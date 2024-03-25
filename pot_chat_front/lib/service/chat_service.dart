import 'package:pot_chat_front/http/http_requests.dart';
import 'package:pot_chat_front/http/request_apis.dart';
import 'package:pot_chat_front/models/common_resp.dart';

class ChatService {
  Future<CommonResp?> create() async {
    Map<String, dynamic> resp =
        await HttpRequest.bodyPost(RequestApi.createSession, params: {});
    return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> list() async {
    Map<String, dynamic> resp =
        await HttpRequest.pathGet(RequestApi.listSessions, params: []);
    return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> delete(String id) async {
    Map<String, dynamic> resp =
        await HttpRequest.pathDelete(RequestApi.deleteSession, params: [id]);
    return CommonResp.fromJson(resp);
  }
}
