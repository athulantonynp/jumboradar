package `in`.jumboradar.utils

import android.content.Context
import android.widget.Toast

class Toasty{

    companion object {
        fun show(context: Context,message:String){

            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }

    }
}