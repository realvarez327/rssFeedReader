package com.example.data

import com.example.rssapp.model.FeedItem
import com.example.rssapp.model.RssChannel
import org.w3c.dom.Element
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
class RssParser {
    private val DESC = "description"
    private val TITLE = "title"
    private val DATE = "pubDate"
    private val LINK = "link"
    private val CHANNEL = "channel"
    private val AUTHOR  = "dc:creator"

    private fun Element.getStringFromElementWithParent(elementName:String, parentElementName:String):String?{
        val listOfParents = getElementsByTagName(parentElementName) ?: return null
        val amountOfParents = listOfParents.length
        for (i in 0 until amountOfParents){
            val item = listOfParents.item(i) as? Element?: continue
            return item.textContent
            //get first usable child element, return its text
        }
        return null
    }

    private fun Element.getStringFromOrphanElement(elementName:String ):String?{
        val listOfElements = getElementsByTagName(elementName) ?: return null
        return listOfElements.item(0)?.textContent

    }

    fun parse(input: InputStream, channelId:Int): RssChannel{
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = builder.parse(input)
        val docEl = doc.documentElement
        //get channel info
        val channelTitle = docEl.getStringFromOrphanElement(TITLE)?:""
        val channelDescription = docEl.getStringFromElementWithParent(elementName = DESC, parentElementName = CHANNEL)?:""
        val channelLink = docEl.getStringFromElementWithParent(elementName = LINK, parentElementName = CHANNEL)?:""

        //get children info
        val feedItems = docEl.getItemsFromChannel(channelTitle)

        val toReturn = RssChannel(
            title = channelTitle,
            link = channelLink,
            description = channelDescription,
            channelChildren = feedItems,
            id = channelId
        )
        return toReturn
    }

    private fun Element.getItemsFromChannel(channelName:String): MutableList<FeedItem>{
        val toReturn = mutableListOf<FeedItem>()
        val itemsListRaw = getElementsByTagName("item")?:return toReturn
        for(i in 0 until itemsListRaw.length){
            val element = itemsListRaw.item(i) as? Element?: continue

            val title = element.getStringFromOrphanElement(TITLE)?:""
            val date = element.getStringFromOrphanElement(DATE)?:""
            val link = element.getStringFromOrphanElement(LINK)?:""
            val desc = element.getStringFromOrphanElement(DESC)?:""
            val author = element.getStringFromOrphanElement(AUTHOR)?:""
            toReturn.add(FeedItem(
                author = author,
                publisher = channelName,
                headline = title,
                link = link,
                description = desc,
                publicationDate = date
            ))
        }
        return toReturn
    }


}