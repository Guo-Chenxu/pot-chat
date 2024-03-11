import 'package:get/get.dart';
import 'package:pot_chat_front/pages/home/index.dart';
import 'package:pot_chat_front/pages/notfound/index.dart';
import 'package:pot_chat_front/pages/register/register_binding.dart';
import 'package:pot_chat_front/pages/register/register_view.dart';

part 'app_routes.dart';

class AppPages {
  static const initial = AppRoutes.home;

  static final List<GetPage> routes = [
    GetPage(name: AppRoutes.home, page: () => const HomeView()),
    GetPage(name: AppRoutes.notfound, page: () => const NotfoundView()),
    GetPage(
      name: AppRoutes.register,
      page: () => const RegisterView(),
      binding: RegisterBinding(),
    ),
  ];
}
