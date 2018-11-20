environments {
    local {
        project {
            env = "local"

        }
        log{
            active = "dev"
        }
        server{
            port=7070
        }



        db {
            url = "jdbc:mysql://rm-bp1lx810m6218roaz2o.mysql.rds.aliyuncs.com:3306/yuebao_local?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true"
            username = "eyee_test"
            password = "Eyee@934"
            driverClassName = "com.mysql.jdbc.Driver"


        }

        twitter{
            snowflake_workerid=0
            snowflake_datacenterid=0
        }

        undertow{
            accessLogDirectory="../logs"
        }

        debug{
            pay=true
        }



    }
    dev {
        project {
            env = "dev"

        }
        log{
            active = "dev"
        }
        server{
            port=7070
        }

        db {
            url = "jdbc:mysql://rm-bp1lx810m6218roaz.mysql.rds.aliyuncs.com:3306/yuebao_local?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true"
            username = "eyee_test"
            password = "Eyee@934"
            driverClassName = "com.mysql.jdbc.Driver"


        }

        twitter{
            snowflake_workerid=0
            snowflake_datacenterid=0
        }

        undertow{
            accessLogDirectory="../logs"
        }

        debug{
            pay=true
        }
    }

    test {
        project {
            env = "test"

        }
        log{
            active = "prod"
        }
        server{
            port=7070
        }

        db {
            url = "jdbc:mysql://rm-bp1lx810m6218roaz2o.mysql.rds.aliyuncs.com:3306/yuebao_local?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true"
            username = "eyee_test"
            password = "Eyee@934"
            driverClassName = "com.mysql.jdbc.Driver"


        }

        twitter{
            snowflake_workerid=0
            snowflake_datacenterid=0
        }

        undertow{
            accessLogDirectory="../logs"
        }

        debug{
            pay=true
        }
    }

    production {
        project {
            env = "production"

        }
        log{
            active = "prod"
        }
        server{
            port=7070
        }

        db {
            url = "jdbc:mysql://rm-bp1lx810m6218roaz2o.mysql.rds.aliyuncs.com:3306/yuebao_local?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true"
            username = "eyee_test"
            password = "Eyee@934"
            driverClassName = "com.mysql.jdbc.Driver"


        }

        twitter{
            snowflake_workerid=0
            snowflake_datacenterid=0
        }

        undertow{
            accessLogDirectory="../logs"
        }

        debug{
            pay=false
        }

    }


}