package com.example.katalog

import com.apollographql.apollo.ApolloClient

val apolloClient: ApolloClient = ApolloClient.builder()
    .serverUrl("https://api-eu-central-1.graphcms.com/v2/cki29pwuczpsr01z90cfsgrcv/master")
    .build()