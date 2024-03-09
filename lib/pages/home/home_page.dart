import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    const METHOD_CHANNEL = 'background_service_flutter';

    void showScankBar(String data) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(data),
          behavior: SnackBarBehavior.floating,
        ),
      );
    }

    void startServiceAndroid() async {
      const START_SERVICE = 'start_serviceAndroid';
      if (Platform.isAndroid) {
        var methodChannel = const MethodChannel(METHOD_CHANNEL);
        String data = await methodChannel.invokeMethod(START_SERVICE);
        showScankBar(data);
      }
    }

    void stopServiceAndroid() async {
      const STOP_SERVICE = 'stop_serviceAndroid';
      if (Platform.isAndroid) {
        var methodChannel = const MethodChannel(METHOD_CHANNEL);
        String data = await methodChannel.invokeMethod(STOP_SERVICE);
        showScankBar(data);
      }
    }

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                startServiceAndroid();
              },
              child: const Text('Start service'),
            ),
            const SizedBox(
              height: 20,
            ),
            ElevatedButton(
              onPressed: () {
                stopServiceAndroid();
              },
              child: const Text('Stop service'),
            ),
          ],
        ),
      ),
    );
  }
}
