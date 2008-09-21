/* 
 *
 * SchemaCrawler
 * http://sourceforge.net/projects/schemacrawler
 * Copyright (c) 2000-2008, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package schemacrawler.tools.util;


import schemacrawler.tools.OutputFormat;
import sf.util.Utilities;

/**
 * Represents an HTML table row.
 * 
 * @author Sualeh Fatehi
 */
final class TableCell
{

  private final OutputFormat outputFormat;
  private final String styleClass;
  private final int colSpan;
  private final String text;

  TableCell(final OutputFormat outputFormat)
  {
    this(outputFormat, 1, "", "");
  }

  TableCell(final OutputFormat outputFormat,
            final int colSpan,
            final String styleClass,
            final String text)
  {
    this.outputFormat = outputFormat;
    this.colSpan = colSpan;
    this.styleClass = styleClass;
    this.text = text;
  }

  TableCell(final OutputFormat outputFormat,
            final String styleClass,
            final String text)
  {
    this(outputFormat, 1, styleClass, text);
  }

  /**
   * Converts the table cell to HTML.
   * 
   * @return HTML
   */
  @Override
  public String toString()
  {
    if (outputFormat == OutputFormat.html)
    {
      return toHtmlString();
    }
    else
    {
      return toPlainTextString();
    }
  }

  /**
   * Converts the table cell to HTML.
   * 
   * @return HTML
   */
  private String toHtmlString()
  {
    final StringBuffer buffer = new StringBuffer();
    buffer.append("<td");
    if (colSpan > 1)
    {
      buffer.append(" colspan='").append(colSpan).append("'");
    }
    if (!Utilities.isBlank(styleClass))
    {
      buffer.append(" class='").append(styleClass).append("'");
    }
    buffer.append(">");
    if (!Utilities.isBlank(text))
    {
      buffer.append(Entities.XML.escape(text));
    }
    else
    {
      buffer.append("&nbsp;");
    }
    buffer.append("</td>");

    return buffer.toString();
  }

  /**
   * Converts the table cell to CSV.
   * 
   * @return CSV
   */
  private String toPlainTextString()
  {
    if (outputFormat == OutputFormat.csv)
    {
      return FormatUtils.escapeAndQuoteForExcelCsv(text);
    }
    else
    {
      return text;
    }
  }

}
