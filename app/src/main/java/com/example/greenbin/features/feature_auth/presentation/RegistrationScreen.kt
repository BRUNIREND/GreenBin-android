package com.example.greenbin.features.feature_auth.presentation


//@Composable
//fun RegistrationScreen() {
//    Scaffold (
//        topBar = {
//            CustomTopAppBar(
//                titleRes =R.string.registration_appbar_text,
//                onBackClick = { TODO() },
//                onActionClick = { TODO() },
//            )
//        }
//    ){  innerPadding ->
//        Surface (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
//        ){
//            RegistrationContent(modifier = Modifier.fillMaxSize())
//        }
//    }
//}
//
//@Composable
//fun RegistrationContent(modifier: Modifier = Modifier){
//    Column(
//        verticalArrangement = Arrangement.Top,
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        var checked by remember {mutableStateOf(true)}
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Имя",
//            isPasswordField = false,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "Имя",
//            keyboardType = KeyboardType.Text,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "E-mail",
//            isPasswordField = false,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "E-mail",
//            keyboardType = KeyboardType.Email,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Пароль",
//            isPasswordField = true,
//            value = "dasdsadsa",
//            onValueChange = {},
//            textLabel = "Пароль",
//            keyboardType = KeyboardType.Password,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Пароль",
//            isPasswordField = true,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "Пароль",
//            keyboardType = KeyboardType.Password,
//        )
//        Spacer(
//            modifier = Modifier.height(4.dp)
//        )
//        Row (
//            modifier = Modifier.height(32.dp).fillMaxWidth().padding(start = 58.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ){
//            Checkbox(
//                checked = checked,
//                modifier = Modifier.size(18.dp),
//                colors = CheckboxDefaults.colors(
//                    checkedColor = colorResource(R.color.primary5), // зелёный при активном
//                    uncheckedColor = Color.Gray,      // серый при неактивном
//                    checkmarkColor = Color.White       // цвет галочки
//                ),
//                onCheckedChange = { checked = it }
//            )
//            Text(
//                modifier = modifier.padding(start = 4.dp).fillMaxWidth(),
//                text = stringResource(R.string.agree_license_agreement),
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomButton(
//            text = "Зарегистрироваться",
//            modifier = Modifier
//                .width(327.dp)
//                .height(52.dp),
//            filled = true,
//            enabled = false
//        ) { }
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        TextButton(
//            onClick = {},
//            ) {
//            Text(
//                text = "Войти",
//                color = colorResource(R.color.primary5),
//                style = MaterialTheme.typography.titleSmall,
//            )
//        }
//    }
//}
//
//@Composable
//@Preview(
//    showSystemUi = true,
//    showBackground = true,)
//fun PreviewRegistrationContent(){
//    RegistrationScreen()
//}