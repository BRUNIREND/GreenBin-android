package com.example.greenbin.features.feature_auth.presentation

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NewPasswordScreen(
//    onBackClick: () -> Unit,
//    onEnterClick: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.padding(start = 16.dp),
//                title = {
//                    Text(
//                        text = stringResource(R.string.new_password_title),
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
//        Surface (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
//        ){
//            NewPasswordContent(
//                modifier = Modifier
//            )
//        }
//
//    }
//
//}
//
//@Composable
//fun NewPasswordContent(modifier: Modifier = Modifier){
//    Column(
//        verticalArrangement = Arrangement.Top,
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            modifier = Modifier.width(327.dp),
//            text = stringResource(R.string.new_password_text),
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Spacer(Modifier.height(28.dp))
//        CustomInput(
//            modifier = Modifier
//                .width(327.dp)
//                .height(52.dp),
//            textLabel = "Пароль",
//            textPlaceholder = "Пароль",
//            isPasswordField = true,
//            value = "asd", //Переписать
//            onValueChange = {},
//            keyboardType = KeyboardType.Password
//        )
//        Spacer(Modifier.height(16.dp))
//        CustomInput(
//            modifier = Modifier
//                .width(327.dp)
//                .height(52.dp),
//            textLabel = "Повторите пароль",
//            textPlaceholder = "Повторите пароль",
//            isPasswordField = true,
//            value = "asd", //Переписать
//            onValueChange = {},
//            keyboardType = KeyboardType.Password
//        )
//        Spacer(Modifier.height(16.dp))
//        CustomButton(
//            modifier = Modifier
//                .width(327.dp)
//                .height(52.dp),
//            text = "Сохранить",
//            filled = true,
//            enabled = false,
//            onClick = {}
//        )
//    }
//}
//
//@Composable
//@Preview
//fun ShowPreviewNewPassword(){
//    NewPasswordScreen(
//        onBackClick = {},
//        onEnterClick = {}
//    )
//}