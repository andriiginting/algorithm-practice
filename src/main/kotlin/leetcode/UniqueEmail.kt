package leetcode

import java.lang.StringBuilder

fun main() {

}

fun numUniqueEmails(emails: Array<String>): Int {
    val uniqueList = mutableSetOf<String>()

    for(email in emails) {
        uniqueList.add(formatEmail(email))
    }

    return uniqueList.size
}

fun formatEmail(email: String): String {
    val formattedEmail = StringBuilder()
    val localName = email.substringBefore("@")
    val domainName = email.substringAfter("@")

    if (hasPlusSignInLocal(localName)) {
        formattedEmail.append(localName.substringBefore("+"))
    } else if (hasDotSignInLocal(localName)) {
        formattedEmail.append(localName.replace(".", ""))
    } else {
        formattedEmail.append(localName)
    }
    formattedEmail.append("@")
    formattedEmail.append(validateDomain(domainName))
    return formattedEmail.toString()
}

fun hasPlusSignInLocal(local: String): Boolean {
    return local.contains("+")
}

fun hasDotSignInLocal(local: String): Boolean {
    return local.contains(".")
}

//"testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.
fun validateDomain(domain: String): String {
    val domainAdd = domain.substringBefore(".com")
    domainAdd.replace(".", "")
    return domainAdd
}