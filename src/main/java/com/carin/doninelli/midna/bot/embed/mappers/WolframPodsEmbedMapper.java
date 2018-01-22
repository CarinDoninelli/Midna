package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.wolfram.entities.Pod;
import com.carin.doninelli.wolfram.entities.SubPod;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.Color;
import java.util.List;

public final class WolframPodsEmbedMapper implements EmbedMapper<List<Pod>> {
    @Override
    public EmbedObject map(List<Pod> object) {
        EmbedBuilder embed = new EmbedBuilder()
                .withColor(Color.red)
                .withTitle("Wolfram Alpha");

        for (Pod pod : object) {
            StringBuilder podContent = new StringBuilder();
            for (SubPod subPod : pod.getSubPods()) {
                String subPodContent = String.join("\n", subPod.getContent());
                podContent.append(subPodContent);
            }

            System.out.println(pod);
            embed.appendField(pod.getTitle(), podContent.toString(), false);
        }

        return embed.build();
    }
}
