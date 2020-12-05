package com.example.plaplixproductos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plaplixproductos.ViewModel.AppViewModel
import com.example.plaplixproductos.model.Local.Product
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment: Fragment(),AdapterProducts.dataProduct {

    private val myViewModel: AppViewModel by activityViewModels()
    lateinit var myAdapter: AdapterProducts

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        myAdapter= AdapterProducts(this)
        val recyclerView=view.Recycler_products
        recyclerView.layoutManager= GridLayoutManager(context, 2)
        recyclerView.adapter=myAdapter




        myViewModel.viewListProducts.observe(viewLifecycleOwner, Observer {

            Log.e("DATOS", it.toString())
            myAdapter.updateAdapter(it)
        })



    }

    override fun passItem(product: Product) {
        myViewModel.ProductDetailSelect(product.id)

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)


    }


}