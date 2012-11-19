(ns com.my-company.foobar.core
  (:require [reply.main :as reply]
            [clojure.tools.nrepl.server :as nrepl]))

(gen-class
  :name com.my-company.foobar.core.nrepl
  :main true
  :prefix "nrepl-")

(defn nrepl-server [port]
  (print (format "Attempting to start nREPL server on localhost:%s..." port))
  (let [server (nrepl/start-server :port (Integer/parseInt port))]
    (println "done")
    server))

(defn nrepl-client [attach]
  (reply/launch-nrepl {:attach attach}))

(defn nrepl-main
  [mode & opts]
  (apply (case mode
           ":server" nrepl-server
           ":client" nrepl-client
           (throw (IllegalArgumentException. "Please specify either :server or :client")))
    opts))
