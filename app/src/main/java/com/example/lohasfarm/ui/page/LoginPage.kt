package com.example.lohasfarm.ui.page


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.example.lohasfarm.R
import com.example.lohasfarm.logic.utils.LfState
import com.example.lohasfarm.logic.viewModel.LoginPageViewModel
import com.example.lohasfarm.ui.main.nav.Actions
import com.example.lohasfarm.ui.theme.LOHASFarmTheme
import com.example.lohasfarm.ui.theme.background
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "LOGIN_PAGE"
private val ITEM_WIDTH = 293.dp

@Composable
fun LoginPage(actions: Actions) {

    val viewModel: LoginPageViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val loginState by viewModel.loginState.observeAsState()

    Scaffold(
        backgroundColor = LOHASFarmTheme.colors.background
    ){ innerPadding ->
        val bottomPadding = innerPadding
        val modifier = Modifier
        Box(contentAlignment = Alignment.TopCenter,

            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp)){
            Image(
                painter = painterResource(id = R.drawable.ic_login_page_background),
                contentDescription = "login_page_background",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds)

            Column(modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 240.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                SwitchButtons(modifier, loginState!!, viewModel)

                if (loginState!!) {
                    LoginButton(modifier, scope, viewModel, actions)
                }


            }


        }
    }

}


@Composable
fun SwitchButtons(modifier: Modifier, loginState: Boolean, viewModel: LoginPageViewModel) {
    Column(
        modifier = modifier.height(32.dp)
            .width(ITEM_WIDTH)
            .padding(0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = modifier.padding(0.dp).height(29.4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Button(
                modifier = modifier.height(29.4.dp)
                    .clickable(
                    interactionSource =  remember { MutableInteractionSource() },
                    indication = null){
                        if (!loginState) {
                            viewModel.changeLoginState()
                        }
            },
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.elevation(0.dp),
                border = BorderStroke(0.dp, Color(0)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0)),
                onClick = {
                    if (!loginState) {
                        viewModel.changeLoginState()
                    }
            }) {
                Text(modifier = modifier
                    .width(42.dp)
                    .height(29.4.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (!loginState) {
                            viewModel.changeLoginState()
                        }
                    },
                    style = MaterialTheme.typography.h2,
                    color = if (loginState) {
                                LOHASFarmTheme.colors.color
                            } else {
                                LOHASFarmTheme.colors.footnote
                            },
                    text = "登录")
            }
            
            Button(modifier=modifier
                .clickable(
                    interactionSource =  remember { MutableInteractionSource() },
                    indication = null){
                        if (loginState) {
                            viewModel.changeLoginState()
                        }
                },
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.elevation(0.dp),
                border = BorderStroke(0.dp, Color(0)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0)
                )
                ,onClick = {
                    if (loginState) {
                        viewModel.changeLoginState()
                    }
                }) {
                Text(modifier = modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (loginState) {
                            viewModel.changeLoginState()
                        }
                    }
                    .width(42.dp)
                    .height(29.4.dp),
                    style = MaterialTheme.typography.h2,
                    color = if (!loginState) {
                                LOHASFarmTheme.colors.color
                            } else {
                                LOHASFarmTheme.colors.footnote
                            },
                    text = "注册")
            }
        }

        Row(modifier = modifier.padding(0.dp).height(1.dp).width(if (loginState) {
                40.dp
            } else {
                105.dp
            }),
            horizontalArrangement = Arrangement.SpaceBetween){

            Spacer(modifier = modifier.width(1.dp).height(1.dp))

            Box(
                modifier = modifier
                    .padding(0.dp)
                    .width(16.64.dp)
                    .height(1.dp)
                    .background(LOHASFarmTheme.colors.color))
        }

    }
}


@Composable
fun LoginButton(modifier: Modifier,
                scope: CoroutineScope,
                viewModel: LoginPageViewModel,
                actions: Actions) {
    Button(
        shape = RoundedCornerShape(23.dp),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LOHASFarmTheme.colors.background),
        onClick = {
            scope.launch(Dispatchers.Main) {
                viewModel.login("15319872135", "123456").await()
                if (LfState.isLogin) {
                    actions.clearBackStack()
                    actions.toMainPage()
                }
            }
        }) {
        Text(modifier = modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        LOHASFarmTheme.colors.color5,
                        LOHASFarmTheme.colors.color
                    )
                )
            )
            .width(ITEM_WIDTH)
            .height(46.dp)
            .padding(127.dp, 11.55.dp),
            color = LOHASFarmTheme.colors.white,
            style = MaterialTheme.typography.button,
            text = "登 录")
    }
}