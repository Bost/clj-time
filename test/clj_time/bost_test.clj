(ns clj-time.bost-test
  (:require [clojure.test :refer :all]
            [clj-time.bost :refer :all])
  (:import org.joda.time.format.PeriodFormatterBuilder
           org.joda.time.DateTime))

(deftest basic
  (testing "basic"
    (is (.equals
         "2 days 1 second ago"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose true :desc-length :long})))
    (is (.equals
         "2d1s ago"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose true :desc-length :short})))
    (is (.equals
         "2 days"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose false :desc-length :long})))
    (is (.equals
         "2d"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose false :desc-length :short})))))

(deftest mixed-types
  (testing "mixed-types"
    (is (.equals
         "2 days 1 second ago"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose true :desc-length :long})))
    (is (.equals
         "2 days 1 second ago"
         (ago-diff
          #inst "2015-07-20T14:26:34.634599000-00:00"
          (new DateTime #inst "2015-07-22T14:26:35.634599000-00:00")
          {:verbose true :desc-length :long})))
    (is (.equals
         "2 days 1 second ago"
         (ago-diff
          (new DateTime #inst "2015-07-20T14:26:34.634599000-00:00")
          #inst "2015-07-22T14:26:35.634599000-00:00"
          {:verbose true :desc-length :long})))
    (is (.equals
         "2 days 1 second ago"
         (ago-diff
          #inst "2015-07-20T14:26:34.634599000-00:00"
          #inst "2015-07-22T14:26:35.634599000-00:00"
          {:verbose true :desc-length :long})))))
