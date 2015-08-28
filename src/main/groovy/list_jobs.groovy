import hudson.model.Hudson
import jenkins.model.Jenkins

import java.util.concurrent.*

def days = 120
def time_frame = days * 24 * 60 * 60
def now = Calendar.instance.time.time / 1000
def count = 0

for (item in Jenkins.instance.items) {
    if (item.getLastBuild() != null) {
        last_build_time = item.getLastBuild().getTimestamp().time
        if (now - last_build_time.time / 1000 > time_frame) {
            println("${++count}. ${item.name}:\t\tLast build at ${last_build_time}")
            try {
              item.doDoWipeOutWorkspace()
            } catch (all) {
              println ("\t\t${all.message}")
            }
        } else if (item.disabled) {
            println("${++count}. ${item.name}:\t\tdisabled")
            try {
              item.doDoWipeOutWorkspace()
            } catch (all) {
              println ("\t\t${all.message}")
            }
        }
    } else {
        println("${++count}. ${item.name}:\t\tnewer build")
    }
}
