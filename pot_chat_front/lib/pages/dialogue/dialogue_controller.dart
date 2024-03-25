import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/models/login.dart';
import 'package:pot_chat_front/models/roleContent.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/pages/register/component/nickname_body.dart';
import 'package:sp_util/sp_util.dart';

class DialogueController extends GetxController {
  static final Logger _logger = Logger();

  final String _nickname =
      User.fromJson(Map.from(SpUtil.getObject(Constants.user)!)).nickname!;

  String get nickname => _nickname;

  final RxList<RoleContent> _history = List<RoleContent>.from([]).obs;

  set history(List<RoleContent> value) => _history.value = value;
  List<RoleContent> get history => _history;

  info(String sessionId) {
    RoleContent roleContent = RoleContent(role: "user", content: "user");
    RoleContent roleContent2 = RoleContent(role: "assistant", content: "assistant");
    _history.add(roleContent);
    _history.add(roleContent2);
  }

  test(String text) {
    _logger.i(text);
  }
}
