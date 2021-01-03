package com.example.webs

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.application.lab1.VolleySingleton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

    class MainActivity() : AppCompatActivity(),Adapter.onClick {
        override fun onClickItem(position: Int) {

        }

    lateinit var progressDialog: ProgressDialog
    lateinit var Adapter: Adapter
    lateinit var casesList: ArrayList<case>

        constructor(parcel: Parcel) : this() {

        }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading ..")
        progressDialog.setCancelable(false)
        casesList = ArrayList()
        getCases()
    }

    private fun getCases() {
        val stringRequest = StringRequest(Request.Method.GET,URLs.GET_CASES,Response.Listener { response ->progressDialog.dismiss()
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                val jsonObject = JSONObject(response)
                    val array = jsonObject.getJSONArray("cases")
                        for (i in 0 until array.length()) {
                        casesList.add(case(
                            array.getJSONObject(i).getString("case_number"),
                            array.getJSONObject(i).getString("case_age"),
                            array.getJSONObject(i).getString("case_gender"),
                            array.getJSONObject(i).getString("case_location"),
                            array.getJSONObject(i).getString("case_diagnose_date"),
                            array.getJSONObject(i).getString("case_source_of_infection"),
                            array.getJSONObject(i).getString("case_condition"),
                            array.getJSONObject(i).getString("case_quarantine"),
                            array.getJSONObject(i).getString("case_community")
                            )
                        )
                    }
                    recyclerView.adapter = Adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
             },Response.ErrorListener { error ->
        })
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

        companion object CREATOR : Parcelable.Creator<MainActivity> {
            override fun createFromParcel(parcel: Parcel): MainActivity {
                return MainActivity(parcel)
            }

            override fun newArray(size: Int): Array<MainActivity?> {
                return arrayOfNulls(size)
            }
        }
    }
