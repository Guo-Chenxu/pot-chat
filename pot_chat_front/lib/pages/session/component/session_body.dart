import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/session/component/drawer_body.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';

class SessionBody extends GetView<SessionController> {
  const SessionBody({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("历史对话"),
        backgroundColor: Colors.cyan,
        centerTitle: true,
      ),
      drawer: DrawerBody(),
    );
  }
}
