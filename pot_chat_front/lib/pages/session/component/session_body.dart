import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/models/session.dart';
import 'package:pot_chat_front/pages/session/component/drawer_body.dart';
import 'package:pot_chat_front/pages/session/component/prompt_body.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

class SessionBody extends GetView<SessionController> {
  SessionBody({super.key});

  final GlobalKey<RefreshIndicatorState> _refreshKey =
      GlobalKey<RefreshIndicatorState>();

  final Logger _logger = Logger();

  Widget _buildItem(BuildContext context, int index) {
    Session session = controller.sessions[index];
    return Column(
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(width: 10),
                Text(session.updateTime ?? ""),
              ],
            ),
            Row(
              children: [
                TextButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.red),
                  ),
                  onPressed: () => {controller.delete(session.sessionId!)},
                  child: const Text(
                    '删除',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                const SizedBox(width: 20),
              ],
            )
          ],
        ),
        ListTile(
          onTap: () {
            Get.toNamed(
                "${AppRoutes.dialogue}?sessionId=${session.sessionId}&description=${session.description}");
          },
          title: Text(session.description!),
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("历史对话"),
        backgroundColor: Colors.cyan,
        centerTitle: true,
      ),
      drawer: DrawerBody(),
      body: RefreshIndicator(
        key: _refreshKey,
        onRefresh: () async {
          controller.list();
        },
        child: GetX<SessionController>(
          init: controller,
          initState: (_) async {
            await controller.list();
          },
          builder: (_) {
            return Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                const SizedBox(height: 10),
                ElevatedButton(
                  onPressed: () => {
                    controller.getPrompts().then((value) {
                      if (value != null && value.isNotEmpty) {
                        _logger.i(value);
                        Get.dialog(
                          PromptBody(content: value),
                        );
                      }
                    })
                  },
                  style:
                      ElevatedButton.styleFrom(backgroundColor: Colors.green),
                  child: const Text(
                    "新建对话",
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                const Divider(),
                Expanded(
                  child: ListView.builder(
                    itemCount: _.sessions.length,
                    itemBuilder: (context, index) {
                      return Column(
                        children: [
                          _buildItem(context, index),
                          const Divider(),
                        ],
                      );
                    },
                  ),
                )
              ],
            );
          },
        ),
      ),
    );
  }
}
