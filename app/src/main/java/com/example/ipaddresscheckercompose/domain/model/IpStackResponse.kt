package com.example.ipaddresscheckercompose.domain.model


import com.google.gson.annotations.SerializedName

data class IpStackResponse(
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("connection")
    var connection: Connection? = null,
    @SerializedName("connection_type")
    var connectionType: Any? = null,
    @SerializedName("continent_code")
    var continentCode: String? = null,
    @SerializedName("continent_name")
    var continentName: String? = null,
    @SerializedName("country_code")
    var countryCode: String? = null,
    @SerializedName("country_name")
    var countryName: String? = null,
    @SerializedName("currency")
    var currency: Currency? = null ,
    @SerializedName("dma")
    var dma: String? = null ,
    @SerializedName("hostname")
    var hostname: String? = null,
    @SerializedName("ip")
    var ip: String? = null,
    @SerializedName("ip_routing_type")
    var ipRoutingType: Any? = null,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("location")
    var location: Location? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("msa")
    var msa: String? = null,
    @SerializedName("radius")
    var radius: Any? = null,
    @SerializedName("region_code")
    var regionCode: String? = null,
    @SerializedName("region_name")
    var regionName: String? = null,
    @SerializedName("security")
    var security: Security? = null,
    @SerializedName("time_zone")
    var timeZone: TimeZone? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("zip")
    var zip: String? = null
) {
    data class Connection(
        @SerializedName("asn")
        var asn: Int? = null,
        @SerializedName("carrier")
        var carrier: String? = null,
        @SerializedName("home")
        var home: Any? = null,
        @SerializedName("isic_code")
        var isicCode: Any? = null,
        @SerializedName("isp")
        var isp: String? = null,
        @SerializedName("naics_code")
        var naicsCode: Any? = null,
        @SerializedName("organization_type")
        var organizationType: Any? = null,
        @SerializedName("sld")
        var sld: String? = null,
        @SerializedName("tld")
        var tld: String? = null
    )

    data class Currency(
        @SerializedName("code")
        var code: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("plural")
        var plural: String? = null,
        @SerializedName("symbol")
        var symbol: String? = null,
        @SerializedName("symbol_native")
        var symbolNative: String? = null
    )

    data class Location(
        @SerializedName("calling_code")
        var callingCode: String? = null,
        @SerializedName("capital")
        var capital: String? = null,
        @SerializedName("country_flag")
        var countryFlag: String? = null,
        @SerializedName("country_flag_emoji")
        var countryFlagEmoji: String? = null,
        @SerializedName("country_flag_emoji_unicode")
        var countryFlagEmojiUnicode: String? = null,
        @SerializedName("geoname_id")
        var geonameId: Int? = null,
        @SerializedName("is_eu")
        var isEu: Boolean? = null,
        @SerializedName("languages")
        var languages: List<Language?>?
    ) {
        data class Language(
            @SerializedName("code")
            var code: String? = null,
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("native")
            var native: String? = null
        )
    }

    data class Security(
        @SerializedName("anonymizer_status")
        var anonymizerStatus: Any? = null,
        @SerializedName("crawler_name")
        var crawlerName: Any? = null,
        @SerializedName("crawler_type")
        var crawlerType: Any? = null,
        @SerializedName("hosting_facility")
        var hostingFacility: Boolean? = null,
        @SerializedName("is_crawler")
        var isCrawler: Boolean? = null,
        @SerializedName("is_proxy")
        var isProxy: Boolean? = null,
        @SerializedName("is_tor")
        var isTor: Boolean? = null,
        @SerializedName("proxy_last_detected")
        var proxyLastDetected: Any? = null,
        @SerializedName("proxy_level")
        var proxyLevel: Any? = null,
        @SerializedName("proxy_type")
        var proxyType: Any? = null,
        @SerializedName("threat_level")
        var threatLevel: String? = null,
        @SerializedName("threat_types")
        var threatTypes: Any? = null,
        @SerializedName("vpn_service")
        var vpnService: Any? = null
    )

    data class TimeZone(
        @SerializedName("code")
        var code: String? = null,
        @SerializedName("current_time")
        var currentTime: String? = null,
        @SerializedName("gmt_offset")
        var gmtOffset: Int? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("is_daylight_saving")
        var isDaylightSaving: Boolean? = null
    )
}