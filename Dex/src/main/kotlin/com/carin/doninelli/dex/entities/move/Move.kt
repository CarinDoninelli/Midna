package com.carin.doninelli.dex.entities.move

import com.carin.doninelli.dex.entities.Contest
import com.carin.doninelli.dex.entities.Descriptions
import com.carin.doninelli.dex.entities.Names
import com.carin.doninelli.dex.entities.type.Type
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Move internal constructor(

        @JsonProperty("index_number")
        val index: Int,

        val names: Names,

        val pp: Int,

        val maxPp: Int,

        val power: Int,

        val accuracy: Int,

        val category: MoveCategory,

        val priority: Int,

        val target: MoveTarget,

        val critical: Int,

        val makesContact: Boolean,

        val affectedByProtect: Boolean,

        val affectedByMagicCoat: Boolean,

        val affectedBySnatch: Boolean,

        val affectedByMirrorMove: Boolean,

        val affectedByKingsRock: Boolean,

        val descriptions: Descriptions,

        val type: Type,

        val contests: List<Contest>

) : Serializable