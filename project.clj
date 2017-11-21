(defproject clojure-tic-tac-toe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe written in Clojure"
  :url "https://github.com/bean00/Clojure-Tic-Tac-Toe"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.clojure/tools.trace "0.7.9"]]
  :main ^:skip-aot clojure-tic-tac-toe.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["startup"]}})
