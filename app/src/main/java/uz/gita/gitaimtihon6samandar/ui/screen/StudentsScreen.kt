package uz.gita.gitaimtihon6samandar.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tapadoo.alerter.Alerter
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.databinding.AddDialogBinding

import uz.gita.gitaimtihon6samandar.databinding.ScreenStudentsBinding
import uz.gita.gitaimtihon6samandar.databinding.StudentAddDialogBinding

import uz.gita.gitaimtihon6samandar.presenter.viewmodel.StudentViewModel

import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.StudentViewModelImpl
import uz.gita.gitaimtihon6samandar.ui.adapter.MainAdapter
import uz.gita.gitaimtihon6samandar.ui.adapter.MyHelper
import uz.gita.gitaimtihon6samandar.ui.adapter.MyHelper2
import uz.gita.gitaimtihon6samandar.ui.adapter.StudentAdapter

class StudentsScreen:Fragment(R.layout.screen_students) {
    private var _binding: ScreenStudentsBinding?= null

    private val viewModel: StudentViewModel by viewModels<StudentViewModelImpl>()

    private val adapter: StudentAdapter by lazy {  StudentAdapter() }
    private val binding get()=_binding!!
    private lateinit var list: RecyclerView
    private lateinit var addBtn: FloatingActionButton
    private val args:StudentsScreenArgs by navArgs<StudentsScreenArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.addDialogOpenLiveData.observe(this){
            addDialog()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list= binding.list
        addBtn =binding.addBtn
        list.adapter=adapter
        viewModel.id=args.id
        viewModel.getAll()

        val touchCallback = MyHelper2(binding.list.adapter as StudentAdapter)

        val touchHelper = ItemTouchHelper(touchCallback)

        touchHelper.attachToRecyclerView(binding.list)
        viewModel.getAllStudents.observe(viewLifecycleOwner){
            if (it.size<1) {
                binding.noSomething.visibility= View.VISIBLE
                list.visibility= View.GONE
            }else{
                binding.noSomething.visibility= View.GONE
                list.visibility= View.VISIBLE
                adapter.submitList(it)
            }

        }
        addBtn.setOnClickListener {
            viewModel.addDialogOpen(args.id)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding= ScreenStudentsBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    private fun addDialog(){
        val builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog).create()
        val binding1=  StudentAddDialogBinding.bind(layoutInflater.inflate(R.layout.student_add_dialog,null))
        val view: View = binding1.root
        builder.setView(view)
        binding1.noBtn.setOnClickListener {
            builder.cancel()
        }
        binding1.yesBtn.setOnClickListener {

            viewModel.addStudent(binding1.studentName.text.toString(),binding1.studentSurname.text.toString(),)
            viewModel.addLiveData.observe(this){
                builder.cancel()
            }
            viewModel.errorAddLiveData.observe(this){
                withIcon(it)
            }
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