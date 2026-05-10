{ pkgs ? import <nixpkgs> {} }:

let
  # Обёртка для запуска Chromium как google-chrome (ожидается Selenium)
  chromeWrapper = pkgs.writeShellScriptBin "google-chrome" ''
    exec ${pkgs.chromium}/bin/chromium "$@"
  '';
in
pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk11
    maven
    chromium
    chromedriver   # <-- добавляем chromedriver из Nixpkgs
    chromeWrapper
    xvfb-run
  ];

  shellHook = ''
    export JAVA_HOME=${pkgs.openjdk11.home}
    export CHROME_DRIVER_PATH="${pkgs.chromedriver}/bin/chromedriver"
    export PATH="${chromeWrapper}/bin:$PATH"
    echo "=== Окружение готово ==="
    echo "Chromedriver: $CHROME_DRIVER_PATH"
    echo "Google-Chrome: $(which google-chrome)"
  '';
}
