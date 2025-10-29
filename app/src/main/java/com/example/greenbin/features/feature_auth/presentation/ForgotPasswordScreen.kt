package com.example.greenbin.features.feature_auth.presentation

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ForgotPasswordScreen(
//    onBackClick: () -> Unit,
//    onEnterClick: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.padding(horizontal = 16.dp),
//                title = {
//                    Text(
//                        text = "Забыли пароль?",
//                        style = MaterialTheme.typography.titleLarge
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(
//                            // Стрелки задаются теперь так
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Назад",
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
//        ) {
//            ForgotPasswordContent()
//        }
//
//    }
//
//}
//
//@Composable
//fun ForgotPasswordContent(){
//    Column (
//
//        verticalArrangement = Arrangement.Top,
//        modifier = Modifier
//            .fillMaxSize()
//            .fillMaxHeight()
//            .padding(top = 10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Text(
//            modifier = Modifier.width(327.dp),
//            text = stringResource(R.string.forgot_password_content),
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Spacer(Modifier.height(28.dp))
//        CustomInput(
//            modifier = Modifier.width(327.dp).height(52.dp),
//            textPlaceholder = "E-mail",
//            isPasswordField = false,
//            value = "",
//            onValueChange = {},
//            textLabel = "E-mail",
//            keyboardType = KeyboardType.Email
//        )
//        Spacer(Modifier.height(16.dp))
//        CustomButton(
//            modifier = Modifier.width(327.dp).height(52.dp),
//            text = "Отправить код",
//            onClick = {},
//            filled = true,
//            enabled = false
//        )
//    }
//}
//
//
//@Composable
//@Preview(
//    showBackground = true,
//    showSystemUi = true)
//fun ShowPreviewForgotPassword(){
//    ForgotPasswordScreen({}, {})
//}