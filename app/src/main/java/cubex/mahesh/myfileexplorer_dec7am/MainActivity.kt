package cubex.mahesh.myfileexplorer_dec7am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isFirstTime = false
        lview.setOnItemClickListener { adapterView, view, i, l ->
            if(isFirstTime == false) {
                if (i == 0) {

                    var path = "/storage/emulated/0"
                    var file = File(path)
                    if(!file.exists()){
                        path = "/storage/sdcard0/"
                        file = File(path)
                    }
                   var files:Array<String> =  file.list()
                   var myadapter = ArrayAdapter<String>(
                            this@MainActivity,
                            android.R.layout.simple_list_item_single_choice,
                            files)
                    lview.adapter = myadapter


                } else {

                    var path = "/storage/extSdCard/"
                    var file = File(path)
                    if(!file.exists()){
                        path = "/storage/sdcard1/"
                        file = File(path)
                    }
                    var files:Array<String> =  file.list()
                    var myadapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_list_item_single_choice,
                        files)
                    lview.adapter = myadapter


                }
                isFirstTime = true
            } // execute only once, first time when click on ListView...
        }


    }
}
