package com.appcoffe.app.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appcoffe.app.R
import com.appcoffe.app.databinding.FragmentNewsBinding
import com.appcoffe.app.ui.home.MainActivity

class NewsFragment : Fragment() {
    private val parent: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by lazy { NewsViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false).apply {
            viewModel = this@NewsFragment.viewModel
            lifecycleOwner = this@NewsFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        binding.recyclerView.adapter = NewsAdapter(parent)
        viewModel.listNews()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.listNews()
        }
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it

        }

        viewModel.actionState.observe(viewLifecycleOwner) {
            if (it.isConsumed) {
                Log.i("ActionState", "isConsumed")
            } else if (!it.isSuccess) {
                Toast.makeText(parent, it.massage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}