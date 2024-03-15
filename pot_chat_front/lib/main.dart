import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/config/global_config.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:sp_util/sp_util.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await GlobalConfig.init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'PotChat',
      debugShowCheckedModeBanner: false,
      enableLog: true,
      initialRoute: AppPages.initial,
      getPages: AppPages.routes,
    );
  }
}
