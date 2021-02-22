(def java7? (.startsWith (System/getProperty "java.version") "1.7"))

(defproject org.clojars.bost/clj-time :lein-v
  :description "A date and time library for Clojure, wrapping Joda Time."
  :url "https://github.com/clj-time/clj-time"
  :mailing-list {:name "clj-time mailing list"
                 :archive "https://groups.google.com/forum/?fromgroups#!forum/clj-time"
                 :post "clj-time@googlegroups.com"}
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"
            :distribution :repo}

  ;; can't use the lein-v from ~/.lein/profiles.clj
  :plugins [[com.roomkey/lein-v "7.2.0"]]

  :deploy-repositories [["clojars" {:url "https://repo.clojars.org"
                                    :username "bost"
                                    :password :env/CLOJARS_TOKEN
                                    :sign-releases false}]]

  :dependencies [[joda-time "2.10.10"]
                 [org.clojure/clojure "1.10.2" :scope "provided"]]
  :min-lein-version "2.0.0"
  :global-vars {*warn-on-reflection* true}
  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.12"]
                                  [seancorfield/next.jdbc "1.1.613"]]
                   :plugins [[codox "0.10.7"]]}
             :midje {:dependencies [[midje "1.9.9"]]
                     :plugins      [[lein-midje "3.2.2"]
                                    [midje-readme "1.0.9"]]
                     :midje-readme {:require "[clj-time.core :as t] [clj-time.predicates :as pr] [clj-time.format :as f] [clj-time.coerce :as c]"}}
             :1.7    {:dependencies [[org.clojure/clojure "1.10.2"]]}
             :1.8    {:dependencies [[org.clojure/clojure "1.10.2"]]}
             :1.9    {:dependencies [[org.clojure/clojure "1.10.2"]
                                     [org.clojure/test.check "1.1.0"]]
                      :test-paths ["test" "test_spec"]}
             :1.10   {:dependencies [[org.clojure/clojure "1.10.2"]
                                     [org.clojure/test.check "1.1.0"]]
                      :test-paths ["test" "test_spec"]}
             :master {:repositories [["snapshots" "https://oss.sonatype.org/content/repositories/snapshots/"]]
                      :dependencies [[org.clojure/clojure "1.11.0-master-SNAPSHOT"]
                                     [org.clojure/test.check "1.1.0"]]
                      :test-paths ["test" "test_spec"]}}

  :aliases {"test-all" ["with-profile"
                        ~(str (when-not java7?
                                ;; 1.10+ requires Java 8+
                                (str "dev,master,midje:" ; 1.11 with spec
                                     "dev,1.10,midje:" ; 1.10 with spec
                                     "dev,default,midje:")) ; 1.10 without spec
                              "dev,1.9,midje:" ; 1.9 with spec
                              "dev,1.8,midje:" ; 1.8 is supported too
                              "dev,1.7,midje") ; 1.7 is earliest we support
                        "test"]})
