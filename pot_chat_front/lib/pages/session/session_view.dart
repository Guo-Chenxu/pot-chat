import 'package:flutter/material.dart';

import 'package:get/get.dart';
import 'package:pot_chat_front/pages/session/component/session_body.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';

class SessionView extends StatelessWidget {
  const SessionView({super.key});

  @override
  Widget build(BuildContext context) {
    return GetBuilder<SessionController>(
      builder: (controller) => const Scaffold(
        body: SessionBody(),
      ),
    );
  }
}
