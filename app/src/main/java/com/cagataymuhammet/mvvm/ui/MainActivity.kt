
package  com.cagataymuhammet.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cagataymuhammet.mvvm.R
import com.cagataymuhammet.mvvm.data.repository.UserLocaleDataRepository
import com.cagataymuhammet.mvvm.data.repository.UserRemoteDataRepository
import com.cagataymuhammet.mvvm.ui.MainViewModel
import com.cagataymuhammet.mvvm.util.Constants

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel?=null
    var textView:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private  fun init()
    {
        textView =findViewById(R.id.txt)

        if(Constants.IS_LOCALE)
        {
            mainViewModel=MainViewModel(UserLocaleDataRepository())
        }
        else
        {
            mainViewModel=MainViewModel(UserRemoteDataRepository())
        }

        getData()

    }


    fun getData()
    {
        mainViewModel?.getUsers()

        mainViewModel?.apply {

            usersLiveData.observe(this@MainActivity, Observer {

                it.run {
                    textView?.setText(it.toString())
                }
            })


            error.observe(this@MainActivity, Observer {

                it.run {
                    Toast.makeText(applicationContext,this.localizedMessage,Toast.LENGTH_LONG).show()
                }

            })

            loading.observe(this@MainActivity, Observer {

                //Handle loading
            })


        }
    }
}