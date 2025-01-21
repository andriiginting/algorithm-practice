fun main() {
    val accountMerger = AccountMerger()

    val input = listOf(
        listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
        listOf("John", "johnsmith@mail.com", "john00@mail.com"),
        listOf("Mary", "mary@mail.com"),
        listOf("John", "johnnybravo@mail.com")
    )

    val result = accountMerger.accountsMerge(input)
    println(result)
}

class AccountMerger {
    private val accMerger = mutableListOf<List<String>>()
    private val listOfAccount = mutableListOf<AccountHolder>()

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        accounts.forEach { accountInternal ->
            populateAccountHolder(accountInternal).also { listOfAccount.add(it) }
        }

        for (idx in 1 until listOfAccount.size) {
            val left = listOfAccount[idx - 1]
            val right = listOfAccount[idx]
            if (isSimilarAtLeastOnce(left, right)) {
                val mergerAccount = mergeAccount(left, right)
                accMerger.add(mergerAccount)
            } else {
                val account = mutableListOf<String>()
                account.add(0, right.name)
                right.emails.forEach { account.add(it) }

                accMerger.add(account)
            }
        }

        return accMerger
    }

    private fun isSimilarAtLeastOnce(accountHolder: AccountHolder, accountHolder2: AccountHolder): Boolean {
        if (accountHolder.name == accountHolder2.name) {
            accountHolder2.emails.forEachIndexed { _, email ->
                if (accountHolder.emails.contains(email)) {
                    accountHolder2.emails.remove(email)
                    return true
                }
            }
        }

        return false
    }

    private fun mergeAccount(accountHolder: AccountHolder, accountHolder2: AccountHolder): List<String> {
        return mutableSetOf<String>().apply {
            add(accountHolder.name)
            addAll(accountHolder2.emails)
            addAll(accountHolder.emails)
        }.toList()
    }

    private fun populateAccountHolder(accountInternal: List<String>): AccountHolder {
        val emails = mutableSetOf<String>()

        accountInternal.forEachIndexed { index, s ->
            if (index != 0) {
                emails.add(s)
            }
        }

        return AccountHolder(accountInternal[0], emails)
    }
}


/**
 * [
 * ["John","johnsmith@mail.com","john_newyork@mail.com"],
 * ["John","johnsmith@mail.com","john00@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]
 * ]
 *
 * output:
 * [
 * ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]]
 */
data class AccountHolder(
    val name: String,
    val emails: MutableSet<String>
)