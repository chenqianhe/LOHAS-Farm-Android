package com.example.lohasfarm.ui.page


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.viewModel.LoginPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.main.nav.Destinations
import com.example.lohasfarm.ui.theme.LOHASFarmTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "LOGIN_PAGE"

@Composable
fun LoginPage(actions: Actions) {

    val viewModel: LoginPageViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = LOHASFarmTheme.colors.background
    ){ innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
            modifier = modifier
                .fillMaxWidth()
                .systemBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                scope.launch(Dispatchers.Main) {
                    viewModel.login("15319872135", "123456").await()
                    if (LfState.isLogin) {
                        actions.clearBackStack()
                        actions.toMainPage()
                    }
                }
            }) {
                Text("Login")
            }
        }
    }

}