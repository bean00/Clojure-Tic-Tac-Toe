# Tic Tac Toe in Clojure

A program to play Tic Tac Toe, with an option to play against a computer. This project includes the core code to support a Tic Tac Toe game. However, it is also a stand-alone project (defaults to a 3x3 sized board).

## Instructions
1. Clone or download this repository onto your local machine
2. Use the console to run the program
    - Make sure you have Leiningen installed (https://leiningen.org/)
    - Run `lein run`
3. Follow the program's instructions
    - Single Player Mode: you vs. a computer
    - Multi Player Mode: you vs. another person

## Testing
- Run `lein test` to run all the tests once
- Run `lein test-refresh` to keep the tests running, and refresh the output anytime the code changes (need plugin below)

## Plugins to Support Development
- To be able to run `lein test-refresh`, include the `lein-test-refresh` plugin (https://github.com/jakemcc/lein-test-refresh)
    - More information about declaring profiles: https://github.com/technomancy/leiningen/blob/master/doc/PROFILES.md#declaring-profiles
- To have colorized and formatted output, include the `ultra` plugin (https://github.com/venantius/ultra)
