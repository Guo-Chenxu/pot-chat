class RequestApi {
  static const String baseUrl = "http://10.29.23.17:36720/api/pot";

  static const String uploadAvatar = "/user/uploadAvatar";
  static const String sendVerifyCode = "/user/sendVerifyCode";
  static const String register = "/user/register";
  static const String login = "/user/login";
  static const String faceLogin = "/user/faceLogin";
  static const String addFace = "/user/addFace";
  static const String logout = "/user/logout";

  static const String createSession = '/chat/create';
  static const String deleteSession = '/chat/delete';
  static const String listSessions = '/chat/list';
  static const String infoSession = '/chat/info';
  static const String chat = '/chat/chat';
}
