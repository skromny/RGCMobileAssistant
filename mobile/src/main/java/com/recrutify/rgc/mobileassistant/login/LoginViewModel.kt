package com.recrutify.rgc.mobileassistant.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.recrutify.rgc.mobileassistant.Model.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var loginRequest: MutableLiveData<LoginRequest> = MutableLiveData<LoginRequest>()

    val name = "LoginViewModel.ok"

    val email: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()

    init {
        email.set("artur.aksamit@ascendious.com")
        password.set("aaksamit")

        //loginResponse = authService.login(LoginRequest("artur.nowak@ascendious.com", "Haslo1234"))

    }

    val user: LiveData<Resource<LoggedUser>> = Transformations
            .switchMap(loginRequest) {request ->
                userRepository.login(request)
            }

    fun login() {
        loginRequest.value = LoginRequest(email.get().orEmpty(), password.get().orEmpty().sha1())
    }

//    fun login()
//    {
//        authService.login(LoginRequest("artur.nowak@ascendious.com", "Haslo1234".sha1())).enqueue(object : Callback<LoggedUser>{
//            override fun onResponse(call: Call<LoggedUser>?, response: Response<LoggedUser>?) {
//                loginResponse.value = response?.body()
//            }
//
//            override fun onFailure(call: Call<LoggedUser>?, t: Throwable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })
//    }

    companion object {
        private fun String.sha1(): String {
            val bytes = this.toString().toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val digest = md.digest(bytes)
            return digest.fold("", { str, it -> str + "%02x".format(it) })
        }
    }
}