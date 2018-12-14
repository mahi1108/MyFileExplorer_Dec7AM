package cubex.mahesh.myfileexplorer_dec7am

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    var path = ""
    var files:Array<String>? = null
    var file:File? = null
    var stack= Stack<String>( )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status : Int = ContextCompat.
            checkSelfPermission(this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE)

    }  //onCreate( )

    override fun onBackPressed() {
      //  super.onBackPressed()
        if(stack.size > 1) {
            stack.pop()
        }
        path = stack.peek()
        file = File(path)
        files =  file!!.list()
        var myadapter = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_list_item_single_choice,
            files)
        lview.adapter = myadapter
    }

    fun  readFiles( )
    {
        var isFirstTime = false
        lview.setOnItemClickListener { adapterView, view, i, l ->
            if(isFirstTime == false) {
                if (i == 0) {

                    path = "/storage/emulated/0/"
                    file = File(path)
                    if(!file!!.exists()){
                        path = "/storage/sdcard0/"
                        file = File(path)
                    }
                    files =  file!!.list()
                    var myadapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_list_item_single_choice,
                        files)
                    lview.adapter = myadapter


                } else {

                    path = "/storage/extSdCard/"
                    file = File(path)
                    if(!file!!.exists()){
                        path = "/storage/sdcard1/"
                        file = File(path)
                    }
                    files =  file!!.list()
                    var myadapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_list_item_single_choice,
                        files)
                    lview.adapter = myadapter


                }
                stack.add(path)
                isFirstTime = true
            } // execute only once, first time when click on ListView...
            else{

                var  temp_path = path+"/"+files!![i]
                var temp_file = File(temp_path)
                if(temp_file!!.isDirectory){

                    path = path+"/"+files!![i]
                    file = File(path)
                    files =  file!!.list()
                    var myadapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_list_item_single_choice,
                        files)
                    lview.adapter = myadapter

                    stack.add(path)

                }else{
                    Toast.makeText(this@MainActivity,
                        "You selected file, no child files for a file ",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

} // MainActivity
