package uz.gita.gitaimtihon6samandar.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tapadoo.alerter.Alerter
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.databinding.AddDialogBinding
import uz.gita.gitaimtihon6samandar.databinding.DialogLogOutBinding
import uz.gita.gitaimtihon6samandar.databinding.ScreenMainBinding
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.MainViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.MainViewModelImpl
import uz.gita.gitaimtihon6samandar.ui.adapter.MainAdapter
import uz.gita.gitaimtihon6samandar.ui.adapter.MyHelper


class MainScreen:Fragment(R.layout.screen_main) {
    private var _binding: ScreenMainBinding?= null

    private val viewModel:MainViewModel by viewModels<MainViewModelImpl>()

    private val adapter:MainAdapter by lazy {  MainAdapter()}
    private val binding get()=_binding!!
    private lateinit var list:RecyclerView
    private lateinit var addBtn:FloatingActionButton
    private val args:MainScreenArgs by navArgs<MainScreenArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.addDialogOpenLiveData.observe(this){
            addDialog()
        }
        viewModel.openStudentScreen.observe(this){
            findNavController().navigate(MainScreenDirections.actionMainScreenToStudentsScreen(it))
        }

        viewModel.logOutLiveData.observe(this){
            findNavController().navigate(MainScreenDirections.actionMainScreenToSignInScreen())
        }
        adapter.move(){from,to->
            viewModel.update(from)
            viewModel.update(to)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list= binding.list
        addBtn =binding.addBtn
        list.adapter=adapter
        viewModel.id=args.id
        viewModel.getAll()

        val touchCallback = MyHelper(binding.list.adapter as MainAdapter)

        val touchHelper = ItemTouchHelper(touchCallback)

        touchHelper.attachToRecyclerView(binding.list)
        viewModel.getAllGroups.observe(viewLifecycleOwner){
            if (it.size<1) {
                binding.noSomething.visibility=View.VISIBLE
                list.visibility=View.GONE
            }else{
                binding.noSomething.visibility=View.GONE
                list.visibility=View.VISIBLE
                adapter.submitList(it)
            }

            }
        addBtn.setOnClickListener {
            viewModel.addDialogOpen(args.id)
        }

        adapter.itemPress {
            viewModel.openStudentScreen(it.id)
        }
        binding.logOut.setOnClickListener {
            logOutDialog()
        }
        adapter.itemDelete {
        }
        adapter.itemEdit {

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding=ScreenMainBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    private fun addDialog(){
        val builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog).create()
        val binding1=  AddDialogBinding.bind(layoutInflater.inflate(R.layout.add_dialog,null))
        val view: View = binding1.root
        builder.setView(view)
        binding1.noBtn.setOnClickListener {
            builder.cancel()
        }
        binding1.yesBtn.setOnClickListener {

                viewModel.addGroup(binding1.groupName.text.toString())
                viewModel.addLiveData.observe(this){
                    builder.cancel()
                }
                viewModel.errorAddLiveData.observe(this){
                    withIcon(it)
                }
        }

        builder.show()

    }
    private fun logOutDialog(){
        val builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog).create()
        val binding1=  DialogLogOutBinding.bind(layoutInflater.inflate(R.layout.dialog_log_out,null))
        val view: View = binding1.root
        builder.setView(view)
        binding1.noBtn.setOnClickListener {
            builder.cancel()
        }
        binding1.yesBtn.setOnClickListener {
            viewModel.logout()
            builder.cancel()
        }

        builder.show()

    }

    fun withIcon(text:String) {
        Alerter.create(requireActivity())
            .setText(text)
            .setIcon(R.drawable.notification)
            .setBackgroundColorRes(R.color.tab_layout_bg)

            .show()
    }
}