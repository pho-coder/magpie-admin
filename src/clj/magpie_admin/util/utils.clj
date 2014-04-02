(ns magpie-admin.util.utils)

(defn get-datetime [long-seconds]
  (.format (java.text.SimpleDateFormat. "yyyy-MM-dd hh:mm:ss")
           (java.util.Date. long-seconds)))
