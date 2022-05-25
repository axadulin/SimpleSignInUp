package uz.codemaster.simplesigninup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import uz.codemaster.simplesigninup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingMain: ActivityMainBinding
    var login: String = "empty"
    var password: String = "empty"
    var fullName: String = "empty"
    var phoneNumber: String = "empty"
    var avatar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        val signState=data?.getStringExtra(Constants.SIGN_STATE)
        bindingMain.txtInfo.visibility = View.VISIBLE
        bindingMain.txtPhone.visibility = View.VISIBLE
        if (requestCode == Constants.REQ_CODE_LOGIN) {
            val l = data?.getStringExtra(Constants.LOGIN)
            val p = data?.getStringExtra(Constants.PWD)
            if (login == l && password == p) {
                bindingMain.imgAvatar.setImageResource(avatar)
                bindingMain.txtInfo.text = fullName
                bindingMain.txtPhone.text = phoneNumber
                bindingMain.btnReg.visibility=View.GONE
                bindingMain.btnLogin.text="Exit"

            } else {
                bindingMain.txtInfo.text = "Credentials are invalid, try again!"
                bindingMain.txtPhone.visibility=View.GONE
                bindingMain.imgAvatar.setImageResource(R.drawable.default_image)
            }


        } else if (requestCode == Constants.REQ_CODE_REG) {
            login = data?.getStringExtra(Constants.LOGIN)!!
            password = data.getStringExtra(Constants.PWD)!!
            fullName = data.getStringExtra(Constants.FULLNAME)!!
            phoneNumber = data.getStringExtra(Constants.PHONE)!!
            avatar = data.getIntExtra(Constants.AVATAR, 0)
            bindingMain.imgAvatar.setImageResource(avatar)
            bindingMain.txtInfo.text = fullName
            bindingMain.txtPhone.text = phoneNumber
            bindingMain.btnReg.visibility=View.GONE
            bindingMain.btnLogin.text="Exit"
        }


    }





        fun OnLogin(view: android.view.View) {
            val loginIntent = Intent(applicationContext, FormActivity::class.java)
            loginIntent.putExtra(Constants.SIGN_STATE, Constants.SIGN_IN)
            startActivityForResult(loginIntent, Constants.REQ_CODE_LOGIN)


        }

        fun OnRegister(view: android.view.View) {
            val regIntent = Intent(applicationContext, FormActivity::class.java)
            regIntent.putExtra(Constants.SIGN_STATE, Constants.SIGN_UP)
            startActivityForResult(regIntent, Constants.REQ_CODE_REG)


        }
    }