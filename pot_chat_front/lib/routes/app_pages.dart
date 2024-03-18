import 'package:get/get.dart';
import 'package:pot_chat_front/middleware/router_auth.dart';
import 'package:pot_chat_front/pages/about/index.dart';
import 'package:pot_chat_front/pages/add_face/add_face_binding.dart';
import 'package:pot_chat_front/pages/add_face/add_face_view.dart';
import 'package:pot_chat_front/pages/init/index.dart';
import 'package:pot_chat_front/pages/login/login_binding.dart';
import 'package:pot_chat_front/pages/login/login_view.dart';
import 'package:pot_chat_front/pages/notfound/index.dart';
import 'package:pot_chat_front/pages/register/register_binding.dart';
import 'package:pot_chat_front/pages/register/register_view.dart';
import 'package:pot_chat_front/pages/session/session_binding.dart';
import 'package:pot_chat_front/pages/session/session_view.dart';

part 'app_routes.dart';

class AppPages {
  static const initial = AppRoutes.init;

  static final List<GetPage> routes = [
    GetPage(name: AppRoutes.init, page: () => const InitView()),
    GetPage(name: AppRoutes.notfound, page: () => const NotfoundView()),
    GetPage(
      name: AppRoutes.register,
      page: () => const RegisterView(),
      binding: RegisterBinding(),
    ),
    GetPage(
      name: AppRoutes.login,
      page: () => const LoginView(),
      binding: LoginBinding(),
    ),
    GetPage(
      name: AppRoutes.session,
      page: () => const SessionView(),
      binding: SessionBinding(),
      middlewares: [RouteAuthMiddleware(priority: 1)],
    ),
    GetPage(
      name: AppRoutes.addFace,
      page: () => const AddFaceView(),
      binding: AddFacebinding(),
      middlewares: [RouteAuthMiddleware(priority: 1)],
    ),
    GetPage(
      name: AppRoutes.about,
      page: () => const AboutView(),
      middlewares: [RouteAuthMiddleware(priority: 1)],
    )
  ];
}
