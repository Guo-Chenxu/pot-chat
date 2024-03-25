import 'package:pot_chat_front/models/roleContent.dart';

class SessionInfoResp {
  String? sessionId;
  List<RoleContent>? history;

  SessionInfoResp({this.sessionId, this.history});

  SessionInfoResp.fromJson(Map<String, dynamic> json) {
    sessionId = json['sessionId'];
    history = json['history'] != null
        ? List<RoleContent>.from(
            json['history'].map((x) => RoleContent.fromJson(x)),
          )
        : null;
  }

  Map<String, dynamic> toJson() {
    return {
      'sessionId': sessionId,
      'history': history != null
          ? List<dynamic>.from(history!.map((x) => x.toJson()))
          : null,
    };
  }
}
