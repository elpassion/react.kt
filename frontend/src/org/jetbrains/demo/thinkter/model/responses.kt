package org.jetbrains.demo.thinkter.model

interface RpcData

data class IndexResponse(val top: List<Thought>, val latest: List<Thought>) : RpcData
data class PostThoughtToken(val user: String, val date: Long, val code: String) : RpcData