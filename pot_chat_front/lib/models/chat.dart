import 'package:pot_chat_front/models/roleContent.dart';

class ChatReq {
  String? sessionId;
  String? content;
  List<RoleContent>? history;

  ChatReq({this.sessionId, this.content, this.history});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = <String, dynamic>{};
    data['sessionId'] = sessionId;
    data['content'] = content;
    if (history != null) {
      data['history'] = history!.map((item) => item.toJson()).toList();
    }
    return data;
  }

  ChatReq.fromJson(Map<String, dynamic> json) {
    sessionId = json['sessionId'];
    content = json['content'];
    history = json['history'] != null
        ? List<RoleContent>.from(
            json['history'].map((item) => RoleContent.fromJson(item)))
        : null;
  }
}
