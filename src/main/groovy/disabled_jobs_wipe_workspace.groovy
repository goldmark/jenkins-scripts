import jenkins.model.Jenkins

Closure query = { it.disabled }
Closure action = {
  try {
    it.doDoWipeOutWorkspace()
  } catch (all) {
    println ("\t\t${all.message}")
  }
}

jobs = Jenkins.instance.items.findAll(query).each(action)
return
