# Tic Tac Toe API in Clojure

An API to play Tic Tac Toe. This project includes the core code to support a Tic Tac Toe game, although it cannot be ran on its own. To actually run the program, use the project at https://github.com/bean00/custom-clojure-ttt.

## Instructions
1. Clone or download this repository onto your local machine
2. Use the console to run the tests
    - Make sure you have Leiningen installed (https://leiningen.org/)
    - Run `lein test`

## More on Testing
- Run `lein test-refresh` to keep the tests running, and refresh the output anytime the code changes (need plugin below)

## Plugins to Support Development
- To be able to run `lein test-refresh`, include the `lein-test-refresh` plugin (https://github.com/jakemcc/lein-test-refresh)
    - More information about declaring profiles: https://github.com/technomancy/leiningen/blob/master/doc/PROFILES.md#declaring-profiles
- To have colorized and formatted output, include the `ultra` plugin (https://github.com/venantius/ultra)
