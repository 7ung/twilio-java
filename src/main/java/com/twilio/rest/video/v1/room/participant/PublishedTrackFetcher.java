/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room.participant;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class PublishedTrackFetcher extends Fetcher<PublishedTrack> {
    private final String pathRoomSid;
    private final String pathParticipantSid;
    private final String pathSid;

    /**
     * Construct a new PublishedTrackFetcher.
     * 
     * @param pathRoomSid Unique Room identifier where this Track is published.
     * @param pathParticipantSid Unique Participant identifier that publishes this
     *                           Track.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     */
    public PublishedTrackFetcher(final String pathRoomSid, 
                                 final String pathParticipantSid, 
                                 final String pathSid) {
        this.pathRoomSid = pathRoomSid;
        this.pathParticipantSid = pathParticipantSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched PublishedTrack
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public PublishedTrack fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            "/v1/Rooms/" + this.pathRoomSid + "/Participants/" + this.pathParticipantSid + "/PublishedTracks/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("PublishedTrack fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return PublishedTrack.fromJson(response.getStream(), client.getObjectMapper());
    }
}