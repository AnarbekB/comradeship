package ru.balmukanov.comradeship.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.balmukanov.comradeship.dto.MetaDto;
import ru.balmukanov.comradeship.entity.Link;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.repository.LinkRepository;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MetaParser {
    private static final String URL_PATTERN = "https?://?[\\w\\d._\\-%/?=&#]+";
    private static final String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png|webp)$";

    private static final Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern IMG_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);

    private final LinkRepository linkRepository;

    public MetaParser(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    void fillMeta(Message message) throws IOException {
        String text = message.getText();

        Matcher matcher = URL_REGEX.matcher(text);
        if (matcher.find()) {
            String url = text.substring(matcher.start(), matcher.end());
            matcher = IMG_REGEX.matcher(url);

            Link link = new Link();
            link.setLink(url);
            message.setLink(link);

            if (matcher.find()) {
                link.setCover(url);
            } else if (!url.contains("youtu")) {
                MetaDto meta = this.getMeta(url);

                link.setCover(meta.getCover());
                link.setDescription(meta.getDescription());
                link.setTitle(meta.getTitle());
            }

            this.linkRepository.save(link);
        }
    }

    private MetaDto getMeta(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Elements title = doc.select("meta[name$=title],meta[property$=title]");
        Elements description = doc.select("meta[name$=description],meta[property$=description]");
        Elements cover = doc.select("meta[name$=image],meta[property$=image]");

        return new MetaDto(
                this.getContent(title.first()),
                this.getContent(description.first()),
                this.getContent(cover.first())
        );
    }

    private String getContent(Element element) {
        return element == null ? "" : element.attr("content");
    }
}