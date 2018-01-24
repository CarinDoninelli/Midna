package com.carin.doninelli.dex.entities.move

import com.fasterxml.jackson.annotation.JsonProperty

enum class MoveTarget {

    @JsonProperty("target")
    Target,

    @JsonProperty("all_adjacent_foes")
    AllAdjacentFoes,

    @JsonProperty("all_foes")
    AllFoes,

    @JsonProperty("user")
    User,

    @JsonProperty("anyone_but_user")
    AnyoneButUser,

    @JsonProperty("user_and_adjacent_ally")
    UserAndAdjacentAlly,

    @JsonProperty("user_and_allies")
    UserAndAllies,

    @JsonProperty("adjacent_ally")
    AdjacentAlly,

    @JsonProperty("all_adjacent")
    AllAdjacent,

    @JsonProperty("everyone")
    Everyone,

    @JsonProperty("target_foe")
    TargetFoe
}