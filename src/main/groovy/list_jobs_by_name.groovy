import jenkins.model.Jenkins

Closure query = { it.name ==~ /^.*1.1.*$/ && it.disabled}
Closure action = { println("${it.name}") }

jobs = Jenkins.instance.items.findAll(query).each(action)
return
