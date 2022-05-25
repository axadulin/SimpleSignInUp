package uz.codemaster.simplesigninup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import uz.codemaster.simplesigninup.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    lateinit var bindingForm:ActivityFormBinding
    private var signState="empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingForm= ActivityFormBinding.inflate(layoutInflater)
        setContentView(bindingForm.root)

         signState=intent.getStringExtra(Constants.SIGN_STATE)!!
        if (signState==Constants.SIGN_IN){
            bindingForm.txtInfoForm.text="Enter your data"
            bindingForm.imgAvatar2.visibility=View.GONE
            bindingForm.edFullName.visibility=View.GONE
            bindingForm.edPhone.visibility=View.GONE
            bindingForm.btnAvatar.visibility=View.GONE
        }else if(signState==Constants.SIGN_UP){
            bindingForm.txtInfoForm.text="Enter your registration data"
        }

    }

    fun OnSend(view: android.view.View) {

        if (signState==Constants.SIGN_UP){

            val personData= Intent()
            personData.putExtra(Constants.LOGIN,bindingForm.edLogin.text.toString())
            personData.putExtra(Constants.PWD,bindingForm.edPwd.text.toString())
            personData.putExtra(Constants.FULLNAME,bindingForm.edFullName.text.toString())
            personData.putExtra(Constants.PHONE,bindingForm.edPhone.text.toString())
            personData.putExtra(Constants.AVATAR,R.drawable.woman)
            setResult(RESULT_OK,personData)
            finish()
        }else if(signState==Constants.SIGN_IN){
            intent.putExtra(Constants.LOGIN,bindingForm.edLogin.text.toString())
            intent.putExtra(Constants.PWD,bindingForm.edPwd.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }



    }

    fun OnAvatar(view: android.view.View) {
        bindingForm.imgAvatar2.visibility=View.VISIBLE
        bindingForm.imgAvatar2.setImageResource(R.drawable.woman)

    }
}