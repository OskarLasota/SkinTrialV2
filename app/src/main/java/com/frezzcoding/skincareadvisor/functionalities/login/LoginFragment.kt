package com.frezzcoding.skincareadvisor.functionalities.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.frezzcoding.skincareadvisor.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.login_view.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_view) {

    lateinit var googleSignInClient : GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("on view created login fragment")
        setUpGoogleClient()

    }

    private fun setUpGoogleClient(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this.requireContext(), gso)

        val account = GoogleSignIn.getLastSignedInAccount(this.requireContext())

        btn_sign_in.setSize(SignInButton.SIZE_STANDARD)

        btn_sign_in.setOnClickListener {
            signIn()
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object: ActivityResultCallback<ActivityResult>{
            override fun onActivityResult(result: ActivityResult) {
                if(result.resultCode == Activity.RESULT_OK){
                    var intent = result.data
                    var task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    handleSignInResult(task)
                }
            }

        })
    }

    private fun handleSignInResult(task : Task<GoogleSignInAccount>){
        try{
            //successfully signed in
            var account = task.getResult(ApiException::class.java)
            var acct = GoogleSignIn.getLastSignedInAccount(this.context)
            acct?.let {

            }

        }catch (e : ApiException){
            //on failure
        }
    }

}