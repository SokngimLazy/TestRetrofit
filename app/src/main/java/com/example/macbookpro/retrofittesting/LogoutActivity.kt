package com.example.macbookpro.retrofittesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.macbookpro.retrofittesting.model.LogoutRequestBody
import com.example.macbookpro.retrofittesting.model.LogoutRequestModel
import com.example.macbookpro.retrofittesting.webservice.WebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        btnLogout.setOnClickListener { _ ->
            val body = LogoutRequestModel(
                    REQ_DATA = LogoutRequestBody(
                            api_key = "oto_logout",
                            token = "83cb676867dc781be565c6cdb1c4de85",
                            driver_code = "drv000001"
                    )
            )
            WebService.requestLive().logout(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({response ->
                        val data = response
                        println("SuccessLogout $data")
                    },{
                        println(it.localizedMessage)
                    })
        }
    }
}
