class ValidateIP {

    fun validIPAddress(queryIP: String): String {
        return when {
            isIPv4Type(queryIP) -> IpType.IPV4.type
            isIPv6Type(queryIP) -> IpType.IPV6.type
            else -> IpType.NEITHER.type
        }
    }

    /**
     *  "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example,
     *  "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and
     *  "192.168@1.1" are invalid IPv4 addresses.
     */
    private fun isIPv4Type(query: String): Boolean {
        val zeroTo255 = "([0-9]|[1-9]\\d{1}|1\\d{2}|2[0-4]\\d|25[0-5])"
        val rgv4 = "$zeroTo255\\.$zeroTo255\\.$zeroTo255\\.$zeroTo255"
        val v4Reg = Regex(rgv4)
        return query.matches(v4Reg)
    }

    /**
     * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
     *
     * 1 <= xi.length <= 4
     *
     * xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f')
     * and upper-case English letters ('A' to 'F').
     *
     * Leading zeros are allowed in xi.
     */
    private fun isIPv6Type(query: String): Boolean {
        val fourHexa = "^[0-9A-Fa-f]+\$"
        val split = query.split(":")

        if (split.size < 8) return false

        for (address in split) {
            if (address.length > 4 || !address.matches(Regex(fourHexa))) {
                return false
            }
        }

        return true
    }

}

enum class IpType(val type: String) {
    IPV4("IPv4"),
    IPV6("IPv6"),
    NEITHER("Neither")
}